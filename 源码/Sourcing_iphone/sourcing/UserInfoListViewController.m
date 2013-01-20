//
//  UserInfoListViewController.m
//  sourcing
//
//  Created by wuzhu on 13-1-17.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "UserInfoListViewController.h"
#import "UserVO.h"
#import "ResponseVO.h"
#import "UIColor+MGExpanded.h"
#import "ApplicationSet.h"
#import "ToastHintUtil.h"
#import "StringUtil.h"
#import "JsonParser.h"
#import "MobileUserService.h"
#import "NewConsultViewController.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Interface

@interface UserInfoListViewController ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties

@property (nonatomic,strong)PullingRefreshTableView *prTableView;

@property (nonatomic,strong)NSMutableArray *infolist;
@property (nonatomic)USER_INFO_TYPE infotype;

@property (nonatomic,strong)NSString *titleText;
@property (nonatomic,strong)UserVO *preUserVO;
@property (nonatomic)BOOL isFirstStart;
@end


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation UserInfoListViewController

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize

/* Outlets ********************************************************************/

/* Public *********************************************************************/

/* Private ********************************************************************/
@synthesize  prTableView = prTableView_;
@synthesize  infolist = infolist_;
@synthesize infotype = infotype_;
@synthesize titleText = titleText_;
@synthesize preUserVO = preUserVO_;
@synthesize isFirstStart = isFirstStart_;

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (void)commonInitUserInfoListViewController
{
    self.isFirstStart = YES;
    preUserVO_ = nil;
}

- (id)initWithNibName:(NSString*)nibNameOrNil bundle:(NSBundle*)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self)
    {
        [self commonInitUserInfoListViewController];
    }
    return self;
}

- (id)initWithCoder:(NSCoder*)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self)
    {
        [self commonInitUserInfoListViewController];
    }
    return self;
}

-(id)initWithInfoType:(USER_INFO_TYPE)kinfotype title:(NSString *)ktitle
{
    self = [super init];
    if(self)
    {
        self.infotype = kinfotype;
        self.titleText = ktitle;
        [self commonInitUserInfoListViewController];
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // your code here
    [self initView];
}

-(void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    UserVO *userVO = [ApplicationSet shareData].getUserVO;
    if(self.isFirstStart)
    {
        self.isFirstStart = NO;
        [self.prTableView launchRefreshing];
    }else if(self.infotype == USER_INFO_TYPE_CONSULT&& self.preUserVO!=nil&&userVO!=nil)
    {
        if(self.preUserVO.allconsultcount!=userVO.allconsultcount)
        {
            [self.prTableView launchRefreshing];
            [ToastHintUtil showHint:NSLocalizedString(@"提交咨询信息成功", @"") parentview:self.view];
        }
    }
    self.preUserVO = [userVO copy];
}

- (void)viewDidUnload {
	// your code here
    [self setInfolist:nil];
    [self setTitleText:nil];
    [self setPrTableView:nil];
    [self setLabelTitle:nil];
    [self setButtonNewConsult:nil];
    [super viewDidUnload];
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Superclass Overrides

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Public methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods

-(void)initView
{
    self.labelTitle.text = self.titleText;
    [self.buttonNewConsult setHidden:self.infotype!=USER_INFO_TYPE_CONSULT];
    [self initTableView];
}

-(void)initTableView
{
    CGRect bounds = CGRectMake(0, 44, self.view.frame.size.width, self.view.frame.size.height);
    bounds.size.height -= 44.0f;
    self.prTableView = [[PullingRefreshTableView alloc] initWithFrame:bounds pullingDelegate:self];
    
    self.prTableView.backgroundColor = CCOLOR_TABLEVIEW_BG;
    self.prTableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    //self.prTableView.separatorColor = CCOLOR_TABLEVIEW_SEL;
    self.prTableView.dataSource = self;
    self.prTableView.delegate = self;
    [self.view addSubview:self.prTableView];
}

-(void)loadData
{
    MobileUserService *service = [[MobileUserService alloc] initWithDelegate:self tag:CINT_TAG_LOADNEWDATA];
    [service setTag2:self.infotype];
    switch (self.infotype) {
        case USER_INFO_TYPE_AUDIT:
            [service getUserAuditInfo:@"0" process:NO processcontent:nil];
            break;
        case USER_INFO_TYPE_DECLARE:
            [service getUserDeclareInfo:@"0" process:NO processcontent:nil];
            break;
        case USER_INFO_TYPE_NOTIFIER:
            [service getUserNotifierInfo:@"0" process:NO processcontent:nil];
            break;
        case USER_INFO_TYPE_CONSULT:
            [service getUserConsultInfo:@"0" process:NO processcontent:nil];
            break;
        default:
            break;
    }
}
-(void)loadMoreData
{
    MobileUserService *service = [[MobileUserService alloc] initWithDelegate:self tag:CINT_TAG_LOADMOREDATA];
    [service setTag2:self.infotype];
    switch (self.infotype) {
        case USER_INFO_TYPE_AUDIT:
            [service getUserAuditInfo:[NSString stringWithFormat:@"%d",[self getBottomid]] process:NO processcontent:nil];
            break;
        case USER_INFO_TYPE_DECLARE:
            [service getUserDeclareInfo:[NSString stringWithFormat:@"%d",[self getBottomid]] process:NO processcontent:nil];
            break;
        case USER_INFO_TYPE_NOTIFIER:
            [service getUserNotifierInfo:[NSString stringWithFormat:@"%d",[self getBottomid]] process:NO processcontent:nil];
            break;
        case USER_INFO_TYPE_CONSULT:
            [service getUserConsultInfo:[NSString stringWithFormat:@"%d",[self getBottomid]] process:NO processcontent:nil];
            break;
        default:
            break;
    }
}


-(void)setTableViewData:(NSMutableArray *)list
{
    self.infolist = list;
    if(self.infolist == nil || [self.infolist count]==0)
    {
        [self.prTableView setSeparatorColor:[UIColor clearColor]];
        [self.prTableView setHeaderOnly:YES];
    }
    else
    {
        [self.prTableView setSeparatorColor:CCOLOR_TABLEVIEW_SEL];
        if ([self.infolist count]%CINT_PAGE_SIZE_DEFAULT == 0) {
            [self.prTableView setHeaderOnly:NO];
        }
        else{
            [self.prTableView setHeaderOnly:YES];
        }
    }
    [self.prTableView reloadData];
}

-(void)addTableViewData:(NSMutableArray *)list
{
    if(list==nil||[list count]==0)
        return;
    if(self.infolist==nil||[self.infolist count]==0)
    {
        [self setTableViewData:list];
        return;
    }
    NSMutableArray *insertIndexPaths = [NSMutableArray arrayWithCapacity:[list count]];
    for(int i = 0 ;i < [list count];i++)
    {
        NSIndexPath *newPath=[NSIndexPath indexPathForRow:[self.infolist count]+i inSection:0];
        [insertIndexPaths addObject:newPath];
    }
    [self.infolist addObjectsFromArray:list];
    [self.prTableView setSeparatorColor:CCOLOR_TABLEVIEW_SEL];
    if ([self.infolist count]%CINT_PAGE_SIZE_DEFAULT == 0) {
        [self.prTableView setHeaderOnly:NO];
    }
    else{
        [self.prTableView setHeaderOnly:YES];
    }
    //重新呼叫UITableView的方法, 來生成行.
    [self.prTableView beginUpdates];
    [self.prTableView insertRowsAtIndexPaths:insertIndexPaths withRowAnimation:UITableViewRowAnimationFade];
    [self.prTableView endUpdates];
}

-(NSInteger)getBottomid
{
    if(self.infolist==nil||[self.infolist count]==0)
        return 0;
    BaseVO *vo = [self.infolist objectAtIndex:[self.infolist count]-1];
    return vo.Id;
}
////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions
- (IBAction)actionReturn:(id)sender {
[self dismissModalViewControllerAnimated:YES];
}

- (IBAction)actionNewConsult:(id)sender {
    NewConsultViewController *vc = [[NewConsultViewController alloc] init];
    [self presentModalViewController:vc animated:YES];
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Delegate methods


-(void)serviceResult:(ResponseVO *)result
{
    [self.prTableView tableViewDidFinishedLoading];
    
    if(![result isSucess])
    {
        [ToastHintUtil showHint:result.msg parentview:self.view];
        return;
    }
    ResponseVO *rvo = [[ResponseVO alloc] init];
    NSMutableArray *list = [JsonParser parseJsonToList:result.msg respVO:&rvo ref:nil];
    if(![rvo isSucess])
    {
        [ToastHintUtil showHint:rvo.msg parentview:self.view];
        return;
    }
    if (result.tag == CINT_TAG_LOADNEWDATA) {
        [self setTableViewData:list];
    }else
    {
        [self addTableViewData:list];
    }
}

-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

-(NSInteger)tableView:(UITableView *)tv numberOfRowsInSection:(NSInteger)section
{
    if(self.infolist == nil||[self.infolist count]==0)
    {
        //加载时移除没有数据显示
        if([(PullingRefreshTableView *)tv isLoading])
            return 0;
        
        //未加载时显示没有数据
        return 1;
    }
    return [self.infolist count];
}
-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if(self.infolist == nil||[self.infolist count]==0)
        return 80.0f;
    //TODO
    BaseVO *vo = [self.infolist objectAtIndex:indexPath.row];
    return [vo heightForCell:indexPath.row];
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if(self.infolist == nil||[self.infolist count]==0)
    {
        if([(PullingRefreshTableView *)tableView isLoading])
            return [BaseVO tableViewWithEmptyData:tableView title:@""];
        else
            return [BaseVO tableViewWithEmptyData:tableView title:NSLocalizedString(@"没有数据,请偿试下拉刷新数据",@"没有数据时提示")];
    }
    //TODO
    BaseVO *vo = [self.infolist objectAtIndex:indexPath.row];
    return [vo tableView:tableView index:indexPath.row];
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

//下拉刷新
-(void)pullingTableViewDidStartRefreshing:(PullingRefreshTableView *)tableView
{
    [self performSelector:@selector(loadData)];
    //[self performSelector:@selector(loadData) withObject:nil afterDelay:0.5f];
}
//上拉加载更多
-(void)pullingTableViewDidStartLoading:(PullingRefreshTableView *)tableView
{
    [self performSelector:@selector(loadMoreData)];
    //[self performSelector:@selector(loadMoreData) withObject:nil afterDelay:0.5f];
}

-(void)scrollViewDidScroll:(UIScrollView *)scrollView
{
    [self.prTableView tableViewDidScroll:scrollView];
}

-(void)scrollViewDidEndDragging:(UIScrollView *)scrollView willDecelerate:(BOOL)decelerate
{
    [self.prTableView tableViewDidEndDragging:scrollView];
}
@end
