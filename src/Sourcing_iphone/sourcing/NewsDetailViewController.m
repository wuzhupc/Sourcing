//
//  NewsDetailViewController.m
//  sourcing
//
//  Created by wuzhu on 13-1-8.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

#import "NewsDetailViewController.h"
#import "ToastHintUtil.h"
#import "StringUtil.h"
#import "UIColor+MGExpanded.h"
#import "ApplicationSet.h"
#import "HtmlUtil.h"
#import "SitelinkUtil.h"
#import "PhotoUtil.h"
#import "DataInterfaceUtil.h"

@interface NewsDetailViewController ()

@end

@implementation NewsDetailViewController

-(id)initWithBaseVO:(BaseVO *)kvo title:(NSString *)ktitle favchangeDelegate:(NSObject<FavDataChangeDelegate> *)delegate
{
    self = [super init];
    if(self)
    {
        _baseVO = kvo;
        _titleText = ktitle;
        _favDelegate = nil;
    }
    return self;
}

-(id)initWithBaseVO:(BaseVO *)kvo title:(NSString *)ktitle
{
    return [self initWithBaseVO:kvo title:ktitle favchangeDelegate:nil];
}

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    self.labelTitle.text = _titleText;
    //判断是否已经收藏过
    NSInteger index = [[FavoriteUtil favoriteUtil] hasFavData:_baseVO];
    [self setFavImageView:index!=-1];
    self.webviewDetail.opaque=NO;
    [self.webviewDetail setBackgroundColor:CCOLOR_BG_WHITE];
    self.webviewDetail.delegate = self;
    [self initDetail];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)viewDidUnload {
    [self setButtonReturn:nil];
    [self setLabelTitle:nil];
    [self setButtonFav:nil];
    [self setButtonShare:nil];
    [self setWebviewDetail:nil];
    [super viewDidUnload];
}
#pragma mark  Action
- (IBAction)actionReturn:(id)sender {
    [self dismissModalViewControllerAnimated:YES];
}

- (IBAction)actionFav:(id)sender {
    NSInteger index = [[FavoriteUtil favoriteUtil] hasFavData:_baseVO];
    if(index==-1)
    {
        [[FavoriteUtil favoriteUtil] addFavData:_baseVO];
        [self setFavImageView:YES];
        [ToastHintUtil showHint:NSLocalizedString(@"已添加到“我的收藏”", @"收藏提示") parentview:self.view];
        if(_favDelegate!=nil)
            [_favDelegate hasAddFavData:0 data:_baseVO];
        return;
    }
    [[FavoriteUtil favoriteUtil] removeFavData:_baseVO];
    [self setFavImageView:NO];
    [ToastHintUtil showHint:NSLocalizedString(@"已取消收藏", @"取消收藏提示") parentview:self.view];
    if(_favDelegate!=nil)
        [_favDelegate hasRemoveFavData:index data:_baseVO];
}

- (IBAction)actionShare:(id)sender {
    //TODO 共享
}
#pragma mark 私有方法

//加载详细信息
-(void)initDetail
{
    if([self.webviewDetail isLoading])
        [self.webviewDetail stopLoading];
    if(_baseVO==nil)
        return;
    [self showLoading];
    __block NewsDetailViewController *nvc = self;
    [_baseVO setHtmlToShow:^(BaseVO *kvo) {
        [nvc setNewsInfo:kvo];
    } failure:^(NSString *kmsg) {
        [nvc setErrorInfo:kmsg];
    }];
}

-(void)setNewsInfo:(BaseVO *)kvo
{
    _detailVO = kvo;
    if(_detailVO == nil)
    {
        [self setErrorInfo:NSLocalizedString(@"很抱歉，资讯内容为空。", @"资讯内容为空提示")];
        return;
    }
    NSString *webcontent = [NSString stringWithFormat:@"%@%@%@%@%@",[HtmlUtil getHtmlHead],[_baseVO getHtmlTitle],[_baseVO getHtmlSubTitle],[_detailVO getHtmlContext],[HtmlUtil getHtmlEnd]];
    [self.webviewDetail loadHTMLString:webcontent baseURL:nil];
}

-(void)setErrorInfo:(NSString *)kmsg
{
    [self hideLoad];
    [ToastHintUtil showHint:kmsg parentview:self.view];
    if(_baseVO == nil)
        return;
    NSString *webcontent = [NSString stringWithFormat:@"%@%@%@%@%@",[HtmlUtil getHtmlHead],[_baseVO getHtmlTitle],[_baseVO getHtmlSubTitle],[HtmlUtil getHtmlErrorHit:kmsg hasreload:YES],[HtmlUtil getHtmlEnd]];
    [self.webviewDetail loadHTMLString:webcontent baseURL:nil];
}

-(void)setFavImageView:(BOOL)kfav
{
    if (kfav) {
        [self.buttonFav setBackgroundImage:[UIImage imageNamed:@"icon_faved"] forState:UIControlStateNormal];
    }else
    {
        [self.buttonFav setBackgroundImage:[UIImage imageNamed:@"icon_fav"] forState:UIControlStateNormal];
    }
}

-(void)showLoading
{
    //显示加载
    if (_HUD) {
        [_HUD removeFromSuperview];
        _HUD = nil;
    }
    _HUD = [[MBProgressHUD alloc] initWithView:self.view];
	[self.view addSubview:_HUD];
    _HUD.labelText = @"加载中...";
	// Set the hud to display with a color
	_HUD.color = CCOLOR_BG_WHITE;
	_HUD.delegate = self;
    [_HUD show:YES];
}

-(void)hideLoad
{
    [_HUD hide:YES];
}

#pragma mark UIWebViewDelegate methods
-(void)webView:(UIWebView *)webView didFailLoadWithError:(NSError *)error
{
    //TODO
    [self hideLoad];
    //[ToastHintUtil showHint:NSLocalizedString(@"", @"")];
}
-(void)webViewDidFinishLoad:(UIWebView *)webView
{
    //TODO 字体
    //[self.webviewDetail stringByEvaluatingJavaScriptFromString:@"document.getElementsByTagName('body')[0].style.webkitTextSizeAdjust= '150%'"];
    [self hideLoad];
}
-(void)webViewDidStartLoad:(UIWebView *)webView
{
    //[self.webviewDetail stringByEvaluatingJavaScriptFromString:@"document.getElementsByTagName('body')[0].style.webkitTextSizeAdjust= '150%'"];
    //[self showLoading];
}

-(BOOL)webView:(UIWebView *)webView shouldStartLoadWithRequest:(NSURLRequest *)request navigationType:(UIWebViewNavigationType)navigationType
{
    //处理跳转
    BOOL result = YES;
    NSURL *requestURL=[request URL];
    NSString *requestStr=[[request URL] absoluteString];
    
    if ([HtmlUtil isReloadLink:requestStr]) {
        [self initDetail];
        return NO;
    }
    
    if([HtmlUtil isImageUrl:requestStr])
    {
        PhotoUtil *photoutil = [[PhotoUtil alloc] initWithImageUrl:requestStr];
        [photoutil showPhotoBrowser:self];
        return NO;
    }
    //TODO 判断是否是站内新闻
//    NSString *newsid = [SitelinkUtil isSiteLink:requestStr];
//    if(![StringUtil isEmptyStr:newsid])
//    {
//        SitelinkUtil *sitelink = [[SitelinkUtil alloc] init];
//        [sitelink processSiteLink:newsid parentViewController:self];
//        return NO;
//    }
    //都不是则打开系统浏览器浏览这个地址
    result = ![[UIApplication sharedApplication] openURL:requestURL];
    return result;
}
#pragma mark MBProgressHUDDelegate methods
-(void)hudWasHidden:(MBProgressHUD *)hud
{
    if(_HUD==nil)
        return;
    [_HUD removeFromSuperview];
	_HUD = nil;
}

@end
