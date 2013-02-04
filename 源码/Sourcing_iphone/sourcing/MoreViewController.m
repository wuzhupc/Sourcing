//
//  MoreViewController.m
//  sourcing
//
//  Created by wuzhu on 13-1-4.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "MoreViewController.h"
#import "MGTableBoxStyled.h"
#import "MGLineStyled.h"
#import "SettingUtil.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Interface

@interface MoreViewController ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties
@property (nonatomic,strong) MGBox *tablesGrid;
@property (nonatomic,strong) MGBox *infotable;
@property (nonatomic,strong) MGBox *othertable;
@property (nonatomic,strong) UILabel *labelFont;
@property (nonatomic,strong) UISwitch *swithPush;

@property (nonatomic,strong) MBProgressHUD *hud;
@end


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation MoreViewController


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize

/* Outlets ********************************************************************/

/* Public *********************************************************************/

/* Private ********************************************************************/
@synthesize tablesGrid = tablesGrid_;
@synthesize infotable = infotable_;
@synthesize othertable = othertable_;
@synthesize labelFont = labelFont_;
@synthesize swithPush = swithPush_;
@synthesize hud = hud_;
////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (void)commonInitMoreViewController
{
    [self.tabBarItem setFinishedSelectedImage:[UIImage imageNamed:@"icon_home_tb_more"] withFinishedUnselectedImage:[UIImage imageNamed:@"icon_home_tb_more"]];
}

- (id)initWithNibName:(NSString*)nibNameOrNil bundle:(NSBundle*)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self)
    {
        [self commonInitMoreViewController];
    }
    return self;
}

- (id)initWithCoder:(NSCoder*)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self)
    {
        [self commonInitMoreViewController];
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    [self initTableGrid];
    
}

- (void)viewDidUnload {
	// your code here
    [self setScrollViewContent:nil];
    [super viewDidUnload];
}

- (void)viewDidAppear:(BOOL)animated {
    [super viewDidAppear:animated];
    [self willAnimateRotationToInterfaceOrientation:self.interfaceOrientation duration:1];
    [self didRotateFromInterfaceOrientation:UIInterfaceOrientationPortrait];
}

#pragma mark - Rotation and resizing

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)o {
    return YES;
}

- (void)willAnimateRotationToInterfaceOrientation:(UIInterfaceOrientation)orient
                                         duration:(NSTimeInterval)duration {
    
    // relayout the sections
    [self.scrollViewContent layoutWithSpeed:duration completion:nil];
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Superclass Overrides

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Public methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods

-(void)initTableGrid
{
    // setup the main scroller (using a grid layout)
    self.scrollViewContent.contentLayoutMode = MGLayoutGridStyle;
    self.scrollViewContent.bottomPadding = 8;
    // the tables grid
    self.tablesGrid = [MGBox boxWithSize:CGSizeMake(320, 0)];
    self.tablesGrid.contentLayoutMode = MGLayoutGridStyle;
    [self.scrollViewContent.boxes addObject:self.tablesGrid];
    //the info table
    self.infotable = [MGBox box];
    [self.tablesGrid.boxes addObject:self.infotable];
    self.infotable.sizingMode = MGResizingShrinkWrap;
    //the other table
    self.othertable = [MGBox box];
    [self.tablesGrid.boxes addObject:self.othertable];
    self.othertable.sizingMode = MGResizingShrinkWrap;
    
    //info table title
    MGTableBoxStyled *menu = MGTableBoxStyled.box;
    [self.infotable.boxes addObject:menu];
    // header line
    MGLineStyled
    *header = [MGLineStyled lineWithLeft:@"资讯设置" right:nil size:ROW_SIZE];
    header.font = [UIFont boldSystemFontOfSize:18];
    [menu.topLines addObject:header];
    
    // layout menu line
    self.labelFont = [[UILabel alloc] initWithFrame:CGRectMake(0, 0, 60, 26)];
    self.labelFont.text = [SettingUtil getFontDescWithIndex:[SettingUtil getFontSetting]];
    self.labelFont.backgroundColor = [UIColor clearColor];
    self.labelFont.textAlignment = NSTextAlignmentRight;
    MGLineStyled *layoutLine = [MGLineStyled lineWithLeft:@"正文字体" right:self.labelFont size:ROW_SIZE];
    [menu.topLines addObject:layoutLine];
    layoutLine.onTap = ^{
        [self fontLayoutSection];
    };
    
    self.swithPush = [[UISwitch alloc] initWithFrame:CGRectZero];
    self.swithPush.on = [SettingUtil getPushSetting];
    [self.swithPush addTarget:self action:@selector(pushSwithAction) forControlEvents:UIControlEventValueChanged];
    MGLineStyled *layoutLine2 = [MGLineStyled lineWithLeft:@"信息推送" right:self.swithPush size:ROW_SIZE];
    [menu.topLines addObject:layoutLine2];
    
    //info table title
    MGTableBoxStyled *menu2 = MGTableBoxStyled.box;
    [self.infotable.boxes addObject:menu2];
    // header line
    MGLineStyled
    *header2 = [MGLineStyled lineWithLeft:@"其他设置" right:nil size:ROW_SIZE];
    header2.font = [UIFont boldSystemFontOfSize:18];
    [menu2.topLines addObject:header2];
    MGLineStyled *lineClearCache = [MGLineStyled lineWithLeft:@"清除缓存" right:nil size:ROW_SIZE];
    [menu2.topLines addObject:lineClearCache];
    lineClearCache.onTap = ^{
        [self clearCacheSection];
    };
    MGLineStyled *lineCheckVer = [MGLineStyled lineWithLeft:@"版本更新" right:nil size:ROW_SIZE];
    [menu2.topLines addObject:lineCheckVer];
    lineCheckVer.onTap = ^{
        [self checkVerSection];
    };
    MGLineStyled *lineAbout = [MGLineStyled lineWithLeft:@"关于" right:nil size:ROW_SIZE];
    [menu2.topLines addObject:lineAbout];
    lineAbout.onTap = ^{
        [self aboutSection];
    };
    [self.tablesGrid layout];
    
}
////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Delegate methods

-(void)actionSheet:(UIActionSheet *)actionSheet clickedButtonAtIndex:(NSInteger)buttonIndex
{
    if (buttonIndex == actionSheet.cancelButtonIndex)
        return;
    [SettingUtil setFontSetting:buttonIndex];
    [self.labelFont setText:[SettingUtil getFontDescWithIndex:buttonIndex]];
}


-(BOOL)iVersionShouldDisplayNewVersion:(NSString *)version details:(NSString *)versionDetails
{
    [self.hud hide:YES];
    //[[iVersion sharedInstance] showVerInfoAlert:version details:versionDetails];
    //返回YES会调用iVersion默认的弹出提示窗口 如果要自己处理提示信息请返回NO
    return YES;
}

-(void)iVersionDidNotDetectNewVersion
{
    [self.hud hide:YES];
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"版本更新检查" message:@"当前已是最新版本!" delegate:nil cancelButtonTitle:@"确定" otherButtonTitles:nil];
    [alert show];
}

-(void)iVersionVersionCheckDidFailWithError:(NSError *)error
{
    [self.hud hide:YES];
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"版本更新检查" message:[NSString stringWithFormat:@"版本更新失败:%@",[error localizedDescription]] delegate:nil cancelButtonTitle:@"确定" otherButtonTitles:nil];
    [alert show];
}
- (void)hudWasHidden:(MBProgressHUD *)hud {
	// Remove HUD from screen when the HUD was hidded
	[self.hud removeFromSuperview];
	self.hud = nil;
}

#pragma mark - Layout features sections
-(void)fontLayoutSection
{
    UIActionSheet *actionsheet = [[UIActionSheet alloc] initWithTitle:@"正文字号" delegate:self cancelButtonTitle:nil destructiveButtonTitle:nil otherButtonTitles:nil];
    NSArray *fontdesc = [SettingUtil getFontDesc];
    for(int i = 0 ;i<[fontdesc count];i++)
    {
        [actionsheet addButtonWithTitle:[fontdesc objectAtIndex:i]];
    }
    [actionsheet addButtonWithTitle:@"取消"];
    [actionsheet setCancelButtonIndex:actionsheet.numberOfButtons-1];
    [actionsheet showInView:self.view];
}
-(void)pushSwithAction
{
    [SettingUtil setPushSetting:self.swithPush.on];
}

-(void)clearCacheSection
{
    //TODO
}
-(void)checkVerSection
{
    self.hud = [[MBProgressHUD alloc] initWithView:self.view.window];
	[self.view.window addSubview:self.hud];
	
	self.hud.delegate = self;
	self.hud.labelText = @"版本更新检查...";
    [self.hud show:YES];
    [iVersion sharedInstance].delegate = self;
    //这里要清除用户选择的忽略版本,否则不会调用iVersionShouldDisplayNewVersion
    [iVersion sharedInstance].ignoredVersion = @"";
    [[iVersion sharedInstance] checkForNewVersion];
    
}
-(void)aboutSection
{
    //TODO
}

@end
